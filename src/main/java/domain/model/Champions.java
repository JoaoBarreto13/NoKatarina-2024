package domain.model;

public record Champions(
    Long id,
    String name,
    String role,
    String lore,
    String imageUrl
) {
    public String generateContextByQuestion(String Question) {
        return """
        Pergunta: %s
        Nome do Campeão: %s
        Função: %s
        Lore (Historia): %s
        """.formatted(Question, this.name, this.role, this.lore);
    }
}
