package com.gurus.mobility.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonRootName("dateComment")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DateCommentDto {
    LocalDate date;
    BigInteger nbrOfComments;
}
