package io.dropwizard.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SizeTest {
    @Test
    public void convertsToTerabytes() throws Exception {
        assertThat(Size.terabytes(2).toTerabytes())
                .isEqualTo(2);
    }

    @Test
    public void convertsToGigabytes() throws Exception {
        assertThat(Size.terabytes(2).toGigabytes())
                .isEqualTo(2048);
    }

    @Test
    public void convertsToMegabytes() throws Exception {
        assertThat(Size.gigabytes(2).toMegabytes())
                .isEqualTo(2048);
    }

    @Test
    public void convertsToKilobytes() throws Exception {
        assertThat(Size.megabytes(2).toKilobytes())
                .isEqualTo(2048);
    }

    @Test
    public void convertsToBytes() throws Exception {
        assertThat(Size.kilobytes(2).toBytes())
                .isEqualTo(2048L);
    }

    @Test
    public void parsesTerabytes() throws Exception {
        assertThat(Size.parse("2TB"))
                .isEqualTo(Size.terabytes(2));

        assertThat(Size.parse("2TiB"))
                .isEqualTo(Size.terabytes(2));

        assertThat(Size.parse("1 terabyte"))
                .isEqualTo(Size.terabytes(1));

        assertThat(Size.parse("2 terabytes"))
                .isEqualTo(Size.terabytes(2));
    }

    @Test
    public void parsesGigabytes() throws Exception {
        assertThat(Size.parse("2GB"))
                .isEqualTo(Size.gigabytes(2));

        assertThat(Size.parse("2GiB"))
                .isEqualTo(Size.gigabytes(2));

        assertThat(Size.parse("1 gigabyte"))
                .isEqualTo(Size.gigabytes(1));

        assertThat(Size.parse("2 gigabytes"))
                .isEqualTo(Size.gigabytes(2));
    }

    @Test
    public void parsesMegabytes() throws Exception {
        assertThat(Size.parse("2MB"))
                .isEqualTo(Size.megabytes(2));

        assertThat(Size.parse("2MiB"))
                .isEqualTo(Size.megabytes(2));

        assertThat(Size.parse("1 megabyte"))
                .isEqualTo(Size.megabytes(1));

        assertThat(Size.parse("2 megabytes"))
                .isEqualTo(Size.megabytes(2));
    }

    @Test
    public void parsesKilobytes() throws Exception {
        assertThat(Size.parse("2KB"))
                .isEqualTo(Size.kilobytes(2));

        assertThat(Size.parse("2KiB"))
                .isEqualTo(Size.kilobytes(2));

        assertThat(Size.parse("1 kilobyte"))
                .isEqualTo(Size.kilobytes(1));

        assertThat(Size.parse("2 kilobytes"))
                .isEqualTo(Size.kilobytes(2));
    }

    @Test
    public void parsesBytes() throws Exception {
        assertThat(Size.parse("2B"))
                .isEqualTo(Size.bytes(2));

        assertThat(Size.parse("1 byte"))
                .isEqualTo(Size.bytes(1));

        assertThat(Size.parse("2 bytes"))
                .isEqualTo(Size.bytes(2));
    }

    @Test
    public void parseSizeWithWhiteSpaces() {
        assertThat(Size.parse("64   kilobytes"))
                .isEqualTo(Size.kilobytes(64));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeCount() {
        Size.parse("three bytes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeUnit() {
        Size.parse("1EB");
    }

    @Test(expected = IllegalArgumentException.class)
    public void unableParseWrongSizeFormat() {
        Size.parse("1 mega byte");
    }

    @Test
    public void isHumanReadable() throws Exception {
        assertThat(Size.gigabytes(3).toString())
                .isEqualTo("3 gigabytes");

        assertThat(Size.kilobytes(1).toString())
                .isEqualTo("1 kilobyte");
    }

    @Test
    public void hasAQuantity() throws Exception {
        assertThat(Size.gigabytes(3).getQuantity())
                .isEqualTo(3);
    }

    @Test
    public void hasAUnit() throws Exception {
        assertThat(Size.gigabytes(3).getUnit())
                .isEqualTo(SizeUnit.GIGABYTES);
    }

    @Test
    public void isComparable() throws Exception {
        // both zero
        assertThat(Size.bytes(0).compareTo(Size.bytes(0))).isEqualTo(0);
        assertThat(Size.bytes(0).compareTo(Size.kilobytes(0))).isEqualTo(0);
        assertThat(Size.bytes(0).compareTo(Size.megabytes(0))).isEqualTo(0);
        assertThat(Size.bytes(0).compareTo(Size.gigabytes(0))).isEqualTo(0);
        assertThat(Size.bytes(0).compareTo(Size.terabytes(0))).isEqualTo(0);

        assertThat(Size.kilobytes(0).compareTo(Size.bytes(0))).isEqualTo(0);
        assertThat(Size.kilobytes(0).compareTo(Size.kilobytes(0))).isEqualTo(0);
        assertThat(Size.kilobytes(0).compareTo(Size.megabytes(0))).isEqualTo(0);
        assertThat(Size.kilobytes(0).compareTo(Size.gigabytes(0))).isEqualTo(0);
        assertThat(Size.kilobytes(0).compareTo(Size.terabytes(0))).isEqualTo(0);

        assertThat(Size.megabytes(0).compareTo(Size.bytes(0))).isEqualTo(0);
        assertThat(Size.megabytes(0).compareTo(Size.kilobytes(0))).isEqualTo(0);
        assertThat(Size.megabytes(0).compareTo(Size.megabytes(0))).isEqualTo(0);
        assertThat(Size.megabytes(0).compareTo(Size.gigabytes(0))).isEqualTo(0);
        assertThat(Size.megabytes(0).compareTo(Size.terabytes(0))).isEqualTo(0);

        assertThat(Size.gigabytes(0).compareTo(Size.bytes(0))).isEqualTo(0);
        assertThat(Size.gigabytes(0).compareTo(Size.kilobytes(0))).isEqualTo(0);
        assertThat(Size.gigabytes(0).compareTo(Size.megabytes(0))).isEqualTo(0);
        assertThat(Size.gigabytes(0).compareTo(Size.gigabytes(0))).isEqualTo(0);
        assertThat(Size.gigabytes(0).compareTo(Size.terabytes(0))).isEqualTo(0);

        assertThat(Size.terabytes(0).compareTo(Size.bytes(0))).isEqualTo(0);
        assertThat(Size.terabytes(0).compareTo(Size.kilobytes(0))).isEqualTo(0);
        assertThat(Size.terabytes(0).compareTo(Size.megabytes(0))).isEqualTo(0);
        assertThat(Size.terabytes(0).compareTo(Size.gigabytes(0))).isEqualTo(0);
        assertThat(Size.terabytes(0).compareTo(Size.terabytes(0))).isEqualTo(0);

        // one zero, one negative
        assertThat(Size.bytes(0)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.bytes(0)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.bytes(0)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.bytes(0)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.bytes(0)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.kilobytes(0)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.kilobytes(0)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.kilobytes(0)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.kilobytes(0)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.kilobytes(0)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.megabytes(0)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.megabytes(0)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.megabytes(0)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.megabytes(0)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.megabytes(0)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.gigabytes(0)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.gigabytes(0)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.gigabytes(0)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.gigabytes(0)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.gigabytes(0)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.terabytes(0)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.terabytes(0)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.terabytes(0)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.terabytes(0)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.terabytes(0)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.bytes(-1)).isLessThan(Size.bytes(0));
        assertThat(Size.bytes(-1)).isLessThan(Size.kilobytes(0));
        assertThat(Size.bytes(-1)).isLessThan(Size.megabytes(0));
        assertThat(Size.bytes(-1)).isLessThan(Size.gigabytes(0));
        assertThat(Size.bytes(-1)).isLessThan(Size.terabytes(0));

        assertThat(Size.kilobytes(-1)).isLessThan(Size.bytes(0));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.kilobytes(0));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.megabytes(0));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.gigabytes(0));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.terabytes(0));

        assertThat(Size.megabytes(-1)).isLessThan(Size.bytes(0));
        assertThat(Size.megabytes(-1)).isLessThan(Size.kilobytes(0));
        assertThat(Size.megabytes(-1)).isLessThan(Size.megabytes(0));
        assertThat(Size.megabytes(-1)).isLessThan(Size.gigabytes(0));
        assertThat(Size.megabytes(-1)).isLessThan(Size.terabytes(0));

        assertThat(Size.gigabytes(-1)).isLessThan(Size.bytes(0));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.kilobytes(0));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.megabytes(0));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.gigabytes(0));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.terabytes(0));

        assertThat(Size.terabytes(-1)).isLessThan(Size.bytes(0));
        assertThat(Size.terabytes(-1)).isLessThan(Size.kilobytes(0));
        assertThat(Size.terabytes(-1)).isLessThan(Size.megabytes(0));
        assertThat(Size.terabytes(-1)).isLessThan(Size.gigabytes(0));
        assertThat(Size.terabytes(-1)).isLessThan(Size.terabytes(0));

        // one zero, one positive
        assertThat(Size.bytes(0)).isLessThan(Size.bytes(1));
        assertThat(Size.bytes(0)).isLessThan(Size.kilobytes(1));
        assertThat(Size.bytes(0)).isLessThan(Size.megabytes(1));
        assertThat(Size.bytes(0)).isLessThan(Size.gigabytes(1));
        assertThat(Size.bytes(0)).isLessThan(Size.terabytes(1));

        assertThat(Size.kilobytes(0)).isLessThan(Size.bytes(1));
        assertThat(Size.kilobytes(0)).isLessThan(Size.kilobytes(1));
        assertThat(Size.kilobytes(0)).isLessThan(Size.megabytes(1));
        assertThat(Size.kilobytes(0)).isLessThan(Size.gigabytes(1));
        assertThat(Size.kilobytes(0)).isLessThan(Size.terabytes(1));

        assertThat(Size.megabytes(0)).isLessThan(Size.bytes(1));
        assertThat(Size.megabytes(0)).isLessThan(Size.kilobytes(1));
        assertThat(Size.megabytes(0)).isLessThan(Size.megabytes(1));
        assertThat(Size.megabytes(0)).isLessThan(Size.gigabytes(1));
        assertThat(Size.megabytes(0)).isLessThan(Size.terabytes(1));

        assertThat(Size.gigabytes(0)).isLessThan(Size.bytes(1));
        assertThat(Size.gigabytes(0)).isLessThan(Size.kilobytes(1));
        assertThat(Size.gigabytes(0)).isLessThan(Size.megabytes(1));
        assertThat(Size.gigabytes(0)).isLessThan(Size.gigabytes(1));
        assertThat(Size.gigabytes(0)).isLessThan(Size.terabytes(1));

        assertThat(Size.terabytes(0)).isLessThan(Size.bytes(1));
        assertThat(Size.terabytes(0)).isLessThan(Size.kilobytes(1));
        assertThat(Size.terabytes(0)).isLessThan(Size.megabytes(1));
        assertThat(Size.terabytes(0)).isLessThan(Size.gigabytes(1));
        assertThat(Size.terabytes(0)).isLessThan(Size.terabytes(1));

        assertThat(Size.bytes(1)).isGreaterThan(Size.bytes(0));
        assertThat(Size.bytes(1)).isGreaterThan(Size.kilobytes(0));
        assertThat(Size.bytes(1)).isGreaterThan(Size.megabytes(0));
        assertThat(Size.bytes(1)).isGreaterThan(Size.gigabytes(0));
        assertThat(Size.bytes(1)).isGreaterThan(Size.terabytes(0));

        assertThat(Size.kilobytes(1)).isGreaterThan(Size.bytes(0));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.kilobytes(0));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.megabytes(0));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.gigabytes(0));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.terabytes(0));

        assertThat(Size.megabytes(1)).isGreaterThan(Size.bytes(0));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.kilobytes(0));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.megabytes(0));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.gigabytes(0));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.terabytes(0));

        assertThat(Size.gigabytes(1)).isGreaterThan(Size.bytes(0));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.kilobytes(0));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.megabytes(0));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.gigabytes(0));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.terabytes(0));

        assertThat(Size.terabytes(1)).isGreaterThan(Size.bytes(0));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.kilobytes(0));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.megabytes(0));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.gigabytes(0));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.terabytes(0));

        // both negative
        assertThat(Size.bytes(-2)).isLessThan(Size.bytes(-1));
        assertThat(Size.bytes(-2)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.bytes(-2)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.bytes(-2)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.bytes(-2)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.kilobytes(-2)).isLessThan(Size.bytes(-1));
        assertThat(Size.kilobytes(-2)).isLessThan(Size.kilobytes(-1));
        assertThat(Size.kilobytes(-2)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.kilobytes(-2)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.kilobytes(-2)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.megabytes(-2)).isLessThan(Size.bytes(-1));
        assertThat(Size.megabytes(-2)).isLessThan(Size.kilobytes(-1));
        assertThat(Size.megabytes(-2)).isLessThan(Size.megabytes(-1));
        assertThat(Size.megabytes(-2)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.megabytes(-2)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.gigabytes(-2)).isLessThan(Size.bytes(-1));
        assertThat(Size.gigabytes(-2)).isLessThan(Size.kilobytes(-1));
        assertThat(Size.gigabytes(-2)).isLessThan(Size.megabytes(-1));
        assertThat(Size.gigabytes(-2)).isLessThan(Size.gigabytes(-1));
        assertThat(Size.gigabytes(-2)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.terabytes(-2)).isLessThan(Size.bytes(-1));
        assertThat(Size.terabytes(-2)).isLessThan(Size.kilobytes(-1));
        assertThat(Size.terabytes(-2)).isLessThan(Size.megabytes(-1));
        assertThat(Size.terabytes(-2)).isLessThan(Size.gigabytes(-1));
        assertThat(Size.terabytes(-2)).isLessThan(Size.terabytes(-1));

        assertThat(Size.bytes(-1)).isGreaterThan(Size.bytes(-2));
        assertThat(Size.bytes(-1)).isGreaterThan(Size.kilobytes(-2));
        assertThat(Size.bytes(-1)).isGreaterThan(Size.megabytes(-2));
        assertThat(Size.bytes(-1)).isGreaterThan(Size.gigabytes(-2));
        assertThat(Size.bytes(-1)).isGreaterThan(Size.terabytes(-2));

        assertThat(Size.kilobytes(-1)).isLessThan(Size.bytes(-2));
        assertThat(Size.kilobytes(-1)).isGreaterThan(Size.kilobytes(-2));
        assertThat(Size.kilobytes(-1)).isGreaterThan(Size.megabytes(-2));
        assertThat(Size.kilobytes(-1)).isGreaterThan(Size.gigabytes(-2));
        assertThat(Size.kilobytes(-1)).isGreaterThan(Size.terabytes(-2));

        assertThat(Size.megabytes(-1)).isLessThan(Size.bytes(-2));
        assertThat(Size.megabytes(-1)).isLessThan(Size.kilobytes(-2));
        assertThat(Size.megabytes(-1)).isGreaterThan(Size.megabytes(-2));
        assertThat(Size.megabytes(-1)).isGreaterThan(Size.gigabytes(-2));
        assertThat(Size.megabytes(-1)).isGreaterThan(Size.terabytes(-2));

        assertThat(Size.gigabytes(-1)).isLessThan(Size.bytes(-2));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.kilobytes(-2));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.megabytes(-2));
        assertThat(Size.gigabytes(-1)).isGreaterThan(Size.gigabytes(-2));
        assertThat(Size.gigabytes(-1)).isGreaterThan(Size.terabytes(-2));

        assertThat(Size.terabytes(-1)).isLessThan(Size.bytes(-2));
        assertThat(Size.terabytes(-1)).isLessThan(Size.kilobytes(-2));
        assertThat(Size.terabytes(-1)).isLessThan(Size.megabytes(-2));
        assertThat(Size.terabytes(-1)).isLessThan(Size.gigabytes(-2));
        assertThat(Size.terabytes(-1)).isGreaterThan(Size.terabytes(-2));

        // both positive
        assertThat(Size.bytes(1)).isLessThan(Size.bytes((2)));
        assertThat(Size.bytes(1)).isLessThan(Size.kilobytes((2)));
        assertThat(Size.bytes(1)).isLessThan(Size.megabytes((2)));
        assertThat(Size.bytes(1)).isLessThan(Size.gigabytes((2)));
        assertThat(Size.bytes(1)).isLessThan(Size.terabytes((2)));

        assertThat(Size.kilobytes(1)).isGreaterThan(Size.bytes((2)));
        assertThat(Size.kilobytes(1)).isLessThan(Size.kilobytes((2)));
        assertThat(Size.kilobytes(1)).isLessThan(Size.megabytes((2)));
        assertThat(Size.kilobytes(1)).isLessThan(Size.gigabytes((2)));
        assertThat(Size.kilobytes(1)).isLessThan(Size.terabytes((2)));

        assertThat(Size.megabytes(1)).isGreaterThan(Size.bytes((2)));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.kilobytes((2)));
        assertThat(Size.megabytes(1)).isLessThan(Size.megabytes((2)));
        assertThat(Size.megabytes(1)).isLessThan(Size.gigabytes((2)));
        assertThat(Size.megabytes(1)).isLessThan(Size.terabytes((2)));

        assertThat(Size.gigabytes(1)).isGreaterThan(Size.bytes((2)));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.kilobytes((2)));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.megabytes((2)));
        assertThat(Size.gigabytes(1)).isLessThan(Size.gigabytes((2)));
        assertThat(Size.gigabytes(1)).isLessThan(Size.terabytes((2)));

        assertThat(Size.terabytes(1)).isGreaterThan(Size.bytes((2)));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.kilobytes((2)));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.megabytes((2)));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.gigabytes((2)));
        assertThat(Size.terabytes(1)).isLessThan(Size.terabytes((2)));

        assertThat(Size.bytes(2)).isGreaterThan(Size.bytes((1)));
        assertThat(Size.bytes(2)).isLessThan(Size.kilobytes((1)));
        assertThat(Size.bytes(2)).isLessThan(Size.megabytes((1)));
        assertThat(Size.bytes(2)).isLessThan(Size.gigabytes((1)));
        assertThat(Size.bytes(2)).isLessThan(Size.terabytes((1)));

        assertThat(Size.kilobytes(2)).isGreaterThan(Size.bytes((1)));
        assertThat(Size.kilobytes(2)).isGreaterThan(Size.kilobytes((1)));
        assertThat(Size.kilobytes(2)).isLessThan(Size.megabytes((1)));
        assertThat(Size.kilobytes(2)).isLessThan(Size.gigabytes((1)));
        assertThat(Size.kilobytes(2)).isLessThan(Size.terabytes((1)));

        assertThat(Size.megabytes(2)).isGreaterThan(Size.bytes((1)));
        assertThat(Size.megabytes(2)).isGreaterThan(Size.kilobytes((1)));
        assertThat(Size.megabytes(2)).isGreaterThan(Size.megabytes((1)));
        assertThat(Size.megabytes(2)).isLessThan(Size.gigabytes((1)));
        assertThat(Size.megabytes(2)).isLessThan(Size.terabytes((1)));

        assertThat(Size.gigabytes(2)).isGreaterThan(Size.bytes((1)));
        assertThat(Size.gigabytes(2)).isGreaterThan(Size.kilobytes((1)));
        assertThat(Size.gigabytes(2)).isGreaterThan(Size.megabytes((1)));
        assertThat(Size.gigabytes(2)).isGreaterThan(Size.gigabytes((1)));
        assertThat(Size.gigabytes(2)).isLessThan(Size.terabytes((1)));

        assertThat(Size.terabytes(2)).isGreaterThan(Size.bytes((1)));
        assertThat(Size.terabytes(2)).isGreaterThan(Size.kilobytes((1)));
        assertThat(Size.terabytes(2)).isGreaterThan(Size.megabytes((1)));
        assertThat(Size.terabytes(2)).isGreaterThan(Size.gigabytes((1)));
        assertThat(Size.terabytes(2)).isGreaterThan(Size.terabytes((1)));

        // one negative, one positive
        assertThat(Size.bytes(-1)).isLessThan(Size.bytes(1));
        assertThat(Size.bytes(-1)).isLessThan(Size.kilobytes(1));
        assertThat(Size.bytes(-1)).isLessThan(Size.megabytes(1));
        assertThat(Size.bytes(-1)).isLessThan(Size.gigabytes(1));
        assertThat(Size.bytes(-1)).isLessThan(Size.terabytes(1));

        assertThat(Size.kilobytes(-1)).isLessThan(Size.bytes(1));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.kilobytes(1));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.megabytes(1));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.gigabytes(1));
        assertThat(Size.kilobytes(-1)).isLessThan(Size.terabytes(1));

        assertThat(Size.megabytes(-1)).isLessThan(Size.bytes(1));
        assertThat(Size.megabytes(-1)).isLessThan(Size.kilobytes(1));
        assertThat(Size.megabytes(-1)).isLessThan(Size.megabytes(1));
        assertThat(Size.megabytes(-1)).isLessThan(Size.gigabytes(1));
        assertThat(Size.megabytes(-1)).isLessThan(Size.terabytes(1));

        assertThat(Size.gigabytes(-1)).isLessThan(Size.bytes(1));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.kilobytes(1));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.megabytes(1));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.gigabytes(1));
        assertThat(Size.gigabytes(-1)).isLessThan(Size.terabytes(1));

        assertThat(Size.terabytes(-1)).isLessThan(Size.bytes(1));
        assertThat(Size.terabytes(-1)).isLessThan(Size.kilobytes(1));
        assertThat(Size.terabytes(-1)).isLessThan(Size.megabytes(1));
        assertThat(Size.terabytes(-1)).isLessThan(Size.gigabytes(1));
        assertThat(Size.terabytes(-1)).isLessThan(Size.terabytes(1));

        assertThat(Size.bytes(1)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.bytes(1)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.bytes(1)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.bytes(1)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.bytes(1)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.kilobytes(1)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.kilobytes(1)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.megabytes(1)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.megabytes(1)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.gigabytes(1)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.gigabytes(1)).isGreaterThan(Size.terabytes(-1));

        assertThat(Size.terabytes(1)).isGreaterThan(Size.bytes(-1));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.kilobytes(-1));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.megabytes(-1));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.gigabytes(-1));
        assertThat(Size.terabytes(1)).isGreaterThan(Size.terabytes(-1));
    }
}
