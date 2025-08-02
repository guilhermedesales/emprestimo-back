export async function salvarEmprestimo(data, valor) {
  try {
    const response = await fetch('/api/emprestimos', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ data, valor })

    });

    if (!response.ok) {
      throw new Error("Erro ao salvar os dados.");
    }

    return true;
  } catch (error) {
    console.error("Erro ao salvar:", error);
    throw error;
  }
}
