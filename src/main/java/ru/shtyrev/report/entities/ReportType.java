package ru.shtyrev.report.entities;

import java.util.Arrays;

public enum ReportType {
    USER, PROJECT, MESSAGE, TASK, FEEDBACK;

    public static boolean exists(String type) {
        return Arrays.stream(values())
                .anyMatch(t -> t.name().equalsIgnoreCase(type));
    }
}
