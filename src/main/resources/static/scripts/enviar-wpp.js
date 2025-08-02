document.addEventListener('DOMContentLoaded', () => {
  const btnWhatsapp = document.getElementById('btn-whatsapp');
  const whatsappContainer = document.getElementById('whatsapp-container');
  const enviarWppBtn = document.getElementById('enviar-wpp');
  const numeroInput = document.getElementById('numero-wpp');

  btnWhatsapp.addEventListener('click', () => {
    whatsappContainer.style.display = whatsappContainer.style.display === 'none' ? 'block' : 'none';
  });

  enviarWppBtn.addEventListener('click', async () => {
    const numero = numeroInput.value.trim();

    if (!numero.match(/^\+\d{11,15}$/)) {
      alert('Digite um número válido com código do país, ex: +5521999999999');
      return;
    }

    try {
      // Busca as transferências
      const responseEmprestimos = await fetch('/api/emprestimos');
      const emprestimos = await responseEmprestimos.json();

      // Busca o resumo das parcelas
      const responseResumo = await fetch('/api/parcelas/resumo');
      const resumo = await responseResumo.json();

      // Monta a mensagem
      let mensagem = '*Transferências Gabrielle*\n\n';
      let totalTransferido = 0;

      emprestimos.forEach(e => {
        const dataStr = e.data + 'T00:00:00';
        const dataFormatada = new Date(dataStr).toLocaleDateString('pt-BR');
        mensagem += `- ${dataFormatada} - R$ ${parseFloat(e.valor).toFixed(2)}\n`;
        totalTransferido += parseFloat(e.valor);
      });

      mensagem += `\nTotal Transferido: R$ ${totalTransferido.toFixed(2)}`;

      const saldo = totalTransferido - resumo.totalPago;
      mensagem += `\nSaldo: R$ ${saldo.toFixed(2)}\n\n`;

      mensagem += '*Parcelas*\n\n';
      mensagem += `- Última parcela paga: ${resumo.ultimaParcelaMesAno}\n`;
      mensagem += `- Parcelas pagas até o momento: ${resumo.quantidadePagas}\n`;
      mensagem += `- Quantas parcelas faltam: ${resumo.quantidadeFaltam}\n`;
      mensagem += `- Total pago em parcelas: R$ ${resumo.totalPago.toFixed(2)}\n`;

      // Abre WhatsApp com a mensagem
      const url = `https://wa.me/${numero.replace('+', '')}?text=${encodeURIComponent(mensagem)}`;
      window.open(url, '_blank');

    } catch (error) {
      alert('Erro ao buscar dados para enviar.');
      console.error(error);
    }
  });
});
