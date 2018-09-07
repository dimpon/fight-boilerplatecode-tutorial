package com.dimpon.tutorals.validation.quickfix;

import com.dimpon.tutorals.validation.quickfix.annotations.IsLimitAndPriceIsSet;
import lombok.Getter;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import quickfix.fix44.NewOrderSingle;

/**
 * @author Dmitrii Ponomarev
 */
@RequiredArgsConstructor(staticName = "of")
@Getter
@IsLimitAndPriceIsSet
public class NewOrderSingleDelegate {


  /*  private interface Add {
     boolean add(String x);

   }*/


    //@Delegate
    private final NewOrderSingle order;

}
