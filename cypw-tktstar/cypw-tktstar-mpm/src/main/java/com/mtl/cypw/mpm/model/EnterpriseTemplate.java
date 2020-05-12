package com.mtl.cypw.mpm.model;

import com.mtl.cypw.common.utils.Money;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tang. 
 * @date 2019年11月26日 上午11:12:46
*/
@Data
@Table(name = "tblenterprisetemplate")
public class EnterpriseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "et_id")
    private Integer etId;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "index_template")
    private String indexTemplate;

    @Column(name = "index_background_color")
    private String indexBackgroundColor;

    @Column(name = "merchant_delivery_types")
    private String merchantDeliveryTypes;

    @Column(name = "merchant_delivery_fee")
    private Integer merchantDeliveryFee;

    @Column(name = "merchant_delivery_restrict")
    private Integer merchantDeliveryRestrict;

    @Column(name = "merchant_delivery_company")
    private String merchantDeliveryCompany;

    public Long gainDeliveryFeeForCent() {
        if (this.merchantDeliveryFee == null) {
            return 0L;
        }
        return new Money(this.merchantDeliveryFee).getCent();
    }

    public Long gainDeliveryRestrictForCent() {
        if (this.merchantDeliveryRestrict == null) {
            return 0L;
        }
        return new Money(this.merchantDeliveryRestrict).getCent();
    }

}