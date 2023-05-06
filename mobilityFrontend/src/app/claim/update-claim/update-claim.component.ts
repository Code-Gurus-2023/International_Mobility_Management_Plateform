import { Component, OnInit } from '@angular/core';
import { Claim } from '../models/claim';
import { ClaimService } from '../services/claim.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.scss']
})
export class UpdateClaimComponent implements OnInit {

  constructor(private claimService:ClaimService, private route:ActivatedRoute, private router: Router) { }

  id:number;
  claim:Claim=new Claim();
  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];
    this.claimService.getClaimById(this.id).subscribe(data => {this.claim =data}, err =>console.log(err));
  }

  onSubmit() {
    console.log(this.claim);
    this.claimService.updateClaim(this.id, this.claim).subscribe(data => {this.goToClaimList()}, err => {console.log(err);});
    this.router.navigate(['/claim']);
  }

  goToClaimList() {
    this.router.navigate(['/claim']);
  }

}
