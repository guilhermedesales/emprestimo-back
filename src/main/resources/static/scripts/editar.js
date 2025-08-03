document.addEventListener("DOMContentLoaded", async () => {
  const btnAbrir = document.getElementById('btn-abrir-edicao');
  const modal = document.getElementById('modal-edicao');
  const select = document.getElementById('emprestimo-select');
  const inputData = document.getElementById('data-edicao');
  const inputValor = document.getElementById('valor-edicao');
  const btnSalvar = document.getElementById('btn-salvar-edicao');

  let emprestimos = [];


  btnAbrir.addEventListener('click', async () => {
    modal.style.display = 'block';
     document.getElementById('overlay').style.display = 'block';

    // carrega empréstimos se ainda não carregou
    if (emprestimos.length === 0) {
      const res = await fetch('/api/emprestimos');
      emprestimos = await res.json();
      select.innerHTML = '';
      emprestimos.forEach(e => {
        const opt = document.createElement('option');
        opt.value = e.id;
        opt.textContent = `${new Date(e.data + 'T00:00:00').toLocaleDateString('pt-BR')} - R$ ${parseFloat(e.valor).toFixed(2)}`;
        select.appendChild(opt);
      });
    }

    preencherCampos();
  });

  select.addEventListener('change', preencherCampos);

  function preencherCampos() {
    const selecionado = emprestimos.find(e => e.id == select.value);
    if (selecionado) {
      inputData.value = selecionado.data;
      inputValor.value = selecionado.valor;
    }
  }

  btnSalvar.addEventListener('click', async () => {
    const id = select.value;
    const body = {
      data: inputData.value,
      valor: parseFloat(inputValor.value)
    };

    const res = await fetch(`/api/emprestimos/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    });

    if (res.ok) {
      alert("Atualizado com sucesso!");
      location.reload();
    } else {
      alert("Erro ao atualizar.");
    }
  });

  window.fecharModal = function () {
    document.getElementById('modal-edicao').style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
  }

});
