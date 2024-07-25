package br.com.alura.challenge.adopet.model;

public enum UserRole {

    TUTOR ("tutor"),
    ABRIGO ("abrigo");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
