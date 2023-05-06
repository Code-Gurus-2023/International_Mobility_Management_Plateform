import { Component, OnInit } from '@angular/core';
import { ClaimService } from '../services/claim.service';
import { Claim } from '../models/claim';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-claim',
  templateUrl: './list-claim.component.html',
  styleUrls: ['./list-claim.component.scss']
})
export class ListClaimComponent implements OnInit {

  constructor(public claimService: ClaimService, private router:Router) { }

  ngOnInit(): void {
    this.getClaimList();
  }
  public claims: Claim[];


  private getClaimList(){
    this.claimService.getClaims().subscribe(data => {this.claims = data;
    console.log(data);});
  }

  updateClaim(id: number){
    this.router.navigate(['claim/updateclaim',id]);
  }

}
