package aula8;

public enum DiaSemana {
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaSemana getDia(int dia){
        return DiaSemana.values()[dia];
    }
}