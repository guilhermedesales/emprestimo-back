function mostrarAba(abaId) {
  document.querySelectorAll('.tab-content').forEach(div => {
    div.style.display = 'none';
  });

  document.querySelectorAll('.tab-button').forEach(btn => {
    btn.classList.remove('active');
  });

  document.getElementById(abaId).style.display = 'block';

  const botaoAtivo = document.querySelector(`button[onclick="mostrarAba('${abaId}')"]`);
  if (botaoAtivo) botaoAtivo.classList.add('active');
}

// Mostra a aba inicial ao carregar a página
document.addEventListener('DOMContentLoaded', function () {
  mostrarAba('transferencias');
});
