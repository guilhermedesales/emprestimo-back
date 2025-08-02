document.addEventListener("DOMContentLoaded", () => {
    const tabelaParcelas = document.querySelector("#tabela-parcelas tbody");
    const totalPagoEl = document.getElementById("total-pago");
    const comparativoEl = document.getElementById("comparativo");
    const filtroAno = document.getElementById("filtro-ano");
    const btnExibirTudo = document.getElementById("btn-exibir-tudo");

    let parcelas = [];
    let transferido = 0;
    let totalPagoGlobal = 0;
    let anoFiltrado = "todos";

    filtroAno.addEventListener("change", () => {
        anoFiltrado = filtroAno.value;
        mostrarParcelas();
    });

    btnExibirTudo.addEventListener("click", () => {
        filtroAno.value = "todos";
        anoFiltrado = "todos";
        mostrarParcelas();
    });

    function carregarTransferencias() {
        fetch('/api/emprestimos/total')
            .then(res => res.json())
            .then(total => {
                transferido = total;
                //atualizarComparativo();
            })
            .catch(err => console.error("Erro ao carregar total transferido:", err));
    }

    function carregarParcelas() {
        fetch('/api/parcelas')
            .then(res => res.json())
            .then(data => {
                parcelas = data;
                mostrarParcelas();
            })
            .catch(err => console.error("Erro ao carregar parcelas:", err));
    }

    function mostrarParcelas() {
        tabelaParcelas.innerHTML = "";

        totalPagoGlobal = parcelas.reduce((acc, parcela) => parcela.pago ? acc + 4021 : acc, 0);

        parcelas.forEach(parcela => {
            if (anoFiltrado !== "todos" && parcela.ano.toString() !== anoFiltrado) return;

            const tr = document.createElement("tr");

            const tdData = document.createElement("td");
            const data = new Date(parcela.ano, parcela.mes - 1, 1);
            tdData.textContent = `${data.toLocaleDateString("pt-BR", { month: "long", year: "numeric" })}`;

            const tdValor = document.createElement("td");
            tdValor.textContent = "R$ 4.021,00";

            const tdPago = document.createElement("td");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.checked = parcela.pago;

            checkbox.addEventListener("change", () => {
                fetch(`/api/parcelas/${parcela.id}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(checkbox.checked)
                })
                .then(() => carregarParcelas())
                .catch(err => console.error("Erro ao atualizar parcela:", err));
            });

            tdPago.appendChild(checkbox);
            tr.appendChild(tdData);
            tr.appendChild(tdValor);
            tr.appendChild(tdPago);
            tabelaParcelas.appendChild(tr);
        });

        totalPagoEl.textContent = `Total Pago: R$ ${totalPagoGlobal.toLocaleString("pt-BR", { minimumFractionDigits: 2 })}`;
        //atualizarComparativo();
    }

    function atualizarComparativo() {
        const diff = transferido - totalPagoGlobal;
        comparativoEl.textContent = `Saldo = R$ ${diff.toLocaleString("pt-BR", { minimumFractionDigits: 2 })}`;
    }

    //carregarTransferencias();
    carregarParcelas();
});
