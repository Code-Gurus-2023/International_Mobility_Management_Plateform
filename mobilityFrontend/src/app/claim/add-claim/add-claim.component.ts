import { Component, OnInit } from '@angular/core';
import { ClaimService } from '../services/claim.service';
import { Router } from '@angular/router';
import { Claim } from '../models/claim';

@Component({
  selector: 'app-add-claim',
  templateUrl: './add-claim.component.html',
  styleUrls: ['./add-claim.component.scss']
})
export class AddClaimComponent implements OnInit {
  claim: Claim=new Claim();
  constructor(private claimService: ClaimService, private router:Router) { }

  ngOnInit(): void {
  }

  saveClaim(){
    this.claimService.creataClaim(this.claim).subscribe(claims => {
      console.log(claims);
    },
    
    error => {
      console.log(error);
    });
  }

  onSubmit() {
    console.log(this.claim);
    this.saveClaim();
    this.router.navigate(['/claim']);

  }


}
