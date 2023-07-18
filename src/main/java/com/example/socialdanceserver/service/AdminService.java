package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.EmailDto;

public interface AdminService {

    void receiveSupportEmail(EmailDto email);

}
