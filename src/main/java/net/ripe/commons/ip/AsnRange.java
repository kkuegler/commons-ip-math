package net.ripe.commons.ip;

public class AsnRange extends AbstractRange<Asn, AsnRange> implements InternetResourceRange<Asn, AsnRange> {

    protected AsnRange(Asn start, Asn end) {
        super(start, end);
    }

    @Override
    protected AsnRange newInstance(Asn start, Asn end) {
        return new AsnRange(start, end);
    }

    public static AsnRangeBuilder from(Long from) {
        return from(Asn.of(from));
    }

    public static AsnRangeBuilder from(Asn from) {
        return new AsnRangeBuilder(from);
    }

    public static AsnRangeBuilder from(String from) {
        return new AsnRangeBuilder(Asn.parse(from));
    }

    public static AsnRange parse(String text) {
        int idx = text.indexOf('-');
        Validate.isTrue(idx != -1, "Invalid range of ASNs: '" + text + "'");
        Asn start = Asn.parse(text.substring(0, idx));
        Asn end = Asn.parse(text.substring(idx + 1));
        return new AsnRange(start, end);
    }

    @Override
    public String toString() {
        return isEmpty() ? start().toString() : start() + "-" + end();
    }

    @Override
    public Long size() {
        return length().value();
    }

    @Override
    public Length<Long> length() {
        return new Length<Long>((end().value() - start().value()) + 1);
    }

    public static class AsnRangeBuilder extends AbstractRangeBuilder<Asn, AsnRange> {
        protected AsnRangeBuilder(Asn from) {
            super(from, AsnRange.class);
        }

        public AsnRange to(Long end) {
            return super.to(Asn.of(end));
        }

        public AsnRange to(String end) {
            return super.to(Asn.parse(end));
        }
    }
}
