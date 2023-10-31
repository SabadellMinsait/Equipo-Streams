package com.minsait.pacientes.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EdadDTO {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate fechaNac = LocalDate.parse("15/08/1993", fmt);
    LocalDate ahora = LocalDate.now();
}
