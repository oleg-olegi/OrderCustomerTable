package com.example.grossbuh.model;

public enum StatusEnum {
    В_ОБРАБОТКЕ("В обработке"),
    ЗАВЕРШЕН("Завершен"),
    НА_СКЛАДЕ("На складе"),
    ОТПРАВЛЕН("Отправлен"),
    ПРИНЯТ("Принят");

    private final String text;

    StatusEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static StatusEnum fromText(String text) {
        StatusEnum[] arrEnum = StatusEnum.values();
        for (StatusEnum status : arrEnum) {
            if (status.text.equals(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with text " + text);
    }
}
