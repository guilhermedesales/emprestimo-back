export async function carregarEmprestimos(tbody, totalSpan) {
  try {
    const [resEmprestimos, resTotalPago] = await Promise.all([
      fetch('/api/emprestimos'),
      fetch('/api/parcelas/total-pago')
    ]);

    const emprestimos = await resEmprestimos.json();
    const totalPago = await resTotalPago.json();

    tbody.innerHTML = '';
    let total = 0;

    emprestimos.forEach(emprestimo => {
      const tr = document.createElement('tr');
      const tdData = document.createElement('td');

      const dataStr = emprestimo.data + 'T00:00:00';
      const dataFormatada = new Date(dataStr).toLocaleDateString('pt-BR');
      tdData.textContent = dataFormatada;

      const tdValor = document.createElement('td');
      tdValor.textContent = `R$ ${parseFloat(emprestimo.valor).toFixed(2)}`;

      total += parseFloat(emprestimo.valor);

      tr.appendChild(tdData);
      tr.appendChild(tdValor);
      tbody.appendChild(tr);
    });

    totalSpan.textContent = `Total: R$ ${total.toFixed(2)}`;

    const saldo = total - totalPago;
    comparativo.textContent = `Saldo = R$ ${saldo.toLocaleString("pt-BR", { minimumFractionDigits: 2 })}`;

  } catch (error) {
    console.error("Erro ao buscar dados:", error);
  }
}
