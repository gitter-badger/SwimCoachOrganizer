package ch.tiim.trainingmanager.lenex;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Currency {
    AUD("Australian dollar"),
    BRL("Brazilian real"),
    CAD("Canadian dollar"),
    CHF("Swiss franc"),
    DKK("Danish krone"),
    DZD("Algerian dinar"),
    GBP("British pound"),
    DR("Indonesian rupiah"),
    EUR("Euro"),
    HRK("Croatian Kuna"),
    INR("Indian rupee"),
    IQD("Iraqi dinar"),
    IRR("Iranian rial"),
    JPY("Japanese yen"),
    KRW("Korea won"),
    KWD("Kuwaiti dinar"),
    MXP("Mexican peso"),
    NGN("Nigerian naira"),
    NOK("Norwegian krone"),
    NZD("New Zealand dollar"),
    PHP("Philippine peso"),
    PKR("Pakistan rupee"),
    PYG("Paraguay guarani"),
    RUR("Russian rouble"),
    SAR("Saudi Arabian riyal"),
    SEK("Swedish krona"),
    TND("Tunisian dinar"),
    USD("US dollar");

    public final String name;

    private Currency(String name) {
        this.name = name;
    }

}
