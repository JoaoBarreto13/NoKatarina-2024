package domain.model;

public record Champions(
    Long id,
    String name,
    String role,
    String lore,
    String imageUrl
) {
    public String generateContextByQuestion(String question) {
        return """
        Você é um assistente especialista em League of Legends. Responda à pergunta do usuário usando as informações do campeão abaixo.
        Responda em português, de forma detalhada e informativa.

        Pergunta: %s

        Informações do Campeão:
        Nome: %s
        Função: %s
        Lore (História): %s
        """.formatted(question, this.name, this.role, this.lore);
    }
}
