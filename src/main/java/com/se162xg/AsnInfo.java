package com.se162xg;

import com.alibaba.fastjson.annotation.JSONField;

public class AsnInfo {

    @JSONField(name = "asn")
    public int ASN;

    @JSONField(name = "reg")
    public String Registry;

    @JSONField(name = "cc")
    public String Country;

    @JSONField(name = "net")
    public String NetName;

    @JSONField(name = "org")
    public String OrgName;
}
