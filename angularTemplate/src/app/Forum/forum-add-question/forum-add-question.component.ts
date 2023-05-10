import { Component, OnInit } from '@angular/core';
import { Discussion } from '../_Models/Discussion.model';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { ForumService } from '../_services/forum.service';

@Component({
  selector: 'app-forum-add-question',
  templateUrl: './forum-add-question.component.html',
  styleUrls: ['./forum-add-question.component.css']
})
export class ForumAddQuestionComponent implements OnInit {

  discussion?: Discussion
  constructor(private forumService: ForumService/*, private router: Router*/, private location: Location) {
    this.discussion = new Discussion("")
   }

  ngOnInit(): void {
  }

  addDiscussion() {
    this.forumService.addDiscussion(this.discussion!)
    .subscribe(
      {
        next:(res) => {
          window.location.reload();
        },
        error:() => {
        }
      }
    )
    console.log("hello")
    // const currentUrl = this.router.url;
    // this.router.navigate([currentUrl])
    //this.router.navigate(['/forum'])
  }

}
