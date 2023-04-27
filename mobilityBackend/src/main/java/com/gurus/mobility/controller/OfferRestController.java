package com.gurus.mobility.controller;

import com.gurus.mobility.entity.Offer.Offer;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.security.jwt.JwtUtils;
import com.gurus.mobility.service.OfferService.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/offer")
public class OfferRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired(required = false)
    private HttpServletResponse response;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private OfferServiceImpl offerService;


    public User authorisation(){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return userRepository.findByUserName(jwtUtils.getUserNameFromJwtToken(token)).get();
    }
    @PostMapping("/create")
    public void addOffer(@RequestBody Offer of){
        User user = authorisation();
        offerService.createOffer(of,user.getId());
    }
}
