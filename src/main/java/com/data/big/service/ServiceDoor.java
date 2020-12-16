package com.data.big.service;

import com.data.big.model.Dooripcrelation;
import com.data.big.vo.Message;

public interface ServiceDoor {
    Message addDooripcrelation(Dooripcrelation dooripcrelation);

    Message updateDooripcrelation(Dooripcrelation dooripcrelation);

    Message deleteDooripcrelation(Dooripcrelation dooripcrelation);

    Message getDooripcrelation(Dooripcrelation dooripcrelation);
}
