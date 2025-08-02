import { salvarEmprestimo } from './salvar-dados.js';
import { carregarEmprestimos } from './tabela.js';

document.addEventListener('DOMContentLoaded', () => {
  const dataInput = document.getElementById('data');
  const valorInput = document.getElementById('valor');
  const salvarBtn = document.getElementById('salvar');
  const verRegistrosBtn = document.getElementById('ver-registros');
  const tabelaContainer = document.getElementById('tabela-container');
  const tbody = document.querySelector('#tabela tbody');
  const totalSpan = document.getElementById('total');

  salvarBtn.addEventListener('click', async (event) => {
    event.preventDefault();
    const data = dataInput.value;
    const valor = parseFloat(valorInput.value);

    if (!data || isNaN(valor)) {
      alert("Preencha todos os campos corretamente.");
      return;
    }

    try {
      await salvarEmprestimo(data, valor);
      alert("Dados salvos com sucesso!");
      dataInput.value = '';
      valorInput.value = '';
      await carregarEmprestimos(tbody, totalSpan);
    } catch (error) {
      alert("Erro ao salvar os dados.");
    }
  });

  verRegistrosBtn.addEventListener('click', () => {
    tabelaContainer.style.display = 'block';
    carregarEmprestimos(tbody, totalSpan);
  });

});