package com.avol.springboot.jersey.util;

import com.avol.springboot.jersey.api.PostalCode;
import com.avol.springboot.jersey.domain.PostalCodeDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 7/10/2015.
 */
public final class ObjectTransferUtil {

    private ObjectTransferUtil(){

    }

    public static PostalCodeDomain postalCodeDomain(PostalCode postalCode) {
        PostalCodeDomain postalCodeDomain = new PostalCodeDomain();
        BeanUtils.copyProperties(postalCode, postalCodeDomain);
        return postalCodeDomain;
    }

    public static PostalCode postalCode(PostalCodeDomain postalCodeDomain) {
        PostalCode postalCode = new PostalCode();
        BeanUtils.copyProperties(postalCodeDomain, postalCode);
        return postalCode;
    }
}
