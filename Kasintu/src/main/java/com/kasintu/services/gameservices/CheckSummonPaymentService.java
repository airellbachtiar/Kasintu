package com.kasintu.services.gameservices;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.userdtos.UserDTO;

public interface CheckSummonPaymentService {
    boolean checkSummonPayment(UserDTO user, BannerDTO banner, int summonTimes);
}
