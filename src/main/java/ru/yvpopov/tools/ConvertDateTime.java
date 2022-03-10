package ru.yvpopov.tools;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertDateTime {

    java.time.Instant instant;

    ZoneOffset zone;

    /**
     *
     * @return
     */
    public ZoneId getZone() {
        return zone;
    }

    /**
     *
     * @param zone
     * @return
     */
    public ConvertDateTime setZone(ZoneOffset zone) {
        this.zone = zone;
        return this;
    }

    /**
     *
     * @return
     */
    public static ConvertDateTime getInstace() {
        return new ConvertDateTime();
    }

    /**
     *
     */
    public ConvertDateTime() {
        this.instant = Instant.now();
        zone = ZoneId.systemDefault().getRules().getOffset(Instant.now());
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(Instant value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(Instant value) {
        this();
        this.instant = value;
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(Calendar value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(Calendar value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(java.util.Date value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(java.util.Date value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(java.sql.Date value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(java.sql.Date value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(OffsetDateTime value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(OffsetDateTime value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(ZonedDateTime value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(ZonedDateTime value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(LocalDateTime value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(LocalDateTime value) {
        this();
        this.instant = value.toInstant(zone);
    }

    /**
     *
     * @param value
     * @param zone
     * @return
     */
    public static ConvertDateTime getInstace(LocalDateTime value, ZoneOffset zone) {
        return new ConvertDateTime(value, zone);
    }

    /**
     *
     * @param value
     * @param zone
     */
    public ConvertDateTime(LocalDateTime value, ZoneOffset zone) {
        this();
        this.zone = zone;
        this.instant = value.toInstant(this.zone);
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(java.sql.Timestamp value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(java.sql.Timestamp value) {
        this();
        this.instant = value.toInstant();
    }

    /**
     *
     * @param value
     * @return
     */
    public static ConvertDateTime getInstace(com.google.protobuf.Timestamp value) {
        return new ConvertDateTime(value);
    }

    /**
     *
     * @param value
     */
    public ConvertDateTime(com.google.protobuf.Timestamp value) {
        this();
        this.instant = Instant.ofEpochSecond(value.getSeconds(), value.getNanos());
    }

    /**
     *
     * @return
     */
    public java.time.Instant toInstant() {
        return this.instant;
    }

    /**
     *
     * @return
     */
    public java.util.Calendar toCalendar() {
        return GregorianCalendar.from(toZonedDateTime());
    }

    /**
     *
     * @return
     */
    public java.util.Date toDate() {
        return Date.from(instant);
    }

    /**
     *
     * @return
     */
    public java.sql.Date toSqlDate() {
        return new java.sql.Date(toDate().getTime());
    }

    /**
     *
     * @return
     */
    public java.sql.Timestamp toSqlTimestamp() {
        return java.sql.Timestamp.from(instant);
    }

    /**
     *
     * @return
     */
    public java.time.OffsetDateTime toOffsetDateTime() {
        return java.time.OffsetDateTime.ofInstant(instant, zone);
    }

    /**
     *
     * @return
     */
    public java.time.ZonedDateTime toZonedDateTime() {
        return ZonedDateTime.ofInstant(instant, zone);
    }

    /**
     *
     * @return
     */
    public java.time.LocalDateTime toLocalDateTime() {
        return java.time.LocalDateTime.ofInstant(instant, zone);
    }

    /**
     *
     * @return
     */
    public com.google.protobuf.Timestamp toTimestamp() {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }

    /**
     *
     * @param amountToAdd
     * @param unit
     * @return
     */
    public ConvertDateTime plus(long amountToAdd, ChronoUnit unit) {
        if (amountToAdd == 0) {
            return this;
        }
        Calendar cl1;
        Calendar cl2;
        switch (unit) {
            case WEEKS: {
                return this.plus(amountToAdd * 7, ChronoUnit.DAYS);
            }
            case MONTHS: {
                cl1 = Calendar.getInstance();
                cl2 = Calendar.getInstance();
                cl2.setTimeInMillis(cl1.getTimeInMillis());
                cl2.add(Calendar.MONTH, (int) amountToAdd);
                return this.plus((cl2.getTimeInMillis() - cl1.getTimeInMillis()), ChronoUnit.MILLIS);
            }
            case YEARS: {
                cl1 = Calendar.getInstance();
                cl2 = Calendar.getInstance();
                cl2.setTimeInMillis(cl1.getTimeInMillis());
                cl2.add(Calendar.YEAR, (int) amountToAdd);
                return this.plus((cl2.getTimeInMillis() - cl1.getTimeInMillis()), ChronoUnit.MILLIS);
            }
            case DECADES: {
                return this.plus(amountToAdd * 10, ChronoUnit.YEARS);
            }
            case CENTURIES: {
                return this.plus(amountToAdd * 100, ChronoUnit.YEARS);
            }
            case MILLENNIA: {
                return this.plus(amountToAdd * 1000, ChronoUnit.YEARS);
            }
            case ERAS: {
                return this.plus(amountToAdd, ChronoUnit.FOREVER);
            }
            case FOREVER: {
                this.instant = (amountToAdd > 0 ? java.time.Instant.MAX : java.time.Instant.MIN);
                break;
            }
            default: {
                instant = instant.plus(amountToAdd, unit);
                break;
            }
        }
        return this;
    }

    /**
     *
     * @param amountToAdd
     * @param unit
     * @return
     */
    public ConvertDateTime minus(long amountToAdd, ChronoUnit unit) {
        return this.plus(amountToAdd * -1L, unit);
    }

    /**
     *
     * @param x
     * @param y
     * @return the value 0 if x == y; a value less than 0 if x < y; and a value greater than 0 if x > y
     */
    public static int Compare(com.google.protobuf.Timestamp x, com.google.protobuf.Timestamp y) {
        return ru.yvpopov.tools.Helper.Compare(x.getSeconds(), x.getNanos(), y.getSeconds(), y.getNanos());
    }

    @Override
    public String toString() {
        return instant.toString();
    }

    /**
     *
     * @param Format (SimpleDateFormat)
     * @return
     */
    public String toString(String Format) {
        return toString(new SimpleDateFormat(Format));
    }

    public String toString(SimpleDateFormat Format) {
        return Format.format(toDate());
    }
    
}
