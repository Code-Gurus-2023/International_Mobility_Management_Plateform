import { Component, OnInit } from '@angular/core';
import { DiscussionDetails } from '../_Models/DiscussionDetails.model';
import { ForumService } from '../_services/forum.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../_Models/Comment.model';
import { error } from 'console';

@Component({
  selector: 'app-forum-discussion',
  templateUrl: './forum-discussion.component.html',
  styleUrls: ['./forum-discussion.component.css']
})
export class ForumDiscussionComponent implements OnInit {

  id: number
  discussionDetails: DiscussionDetails
  comment : Comment 

  constructor(private forumService: ForumService, private router: Router, private route: ActivatedRoute) {
    this.comment = new Comment("");
   }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.getDetails()
  }

  getDetails() {
    return this.forumService.getDiscussionDetails(this.id).subscribe(
      discussionDetails => {
        this.discussionDetails = discussionDetails;
        console.log(discussionDetails)
      },
      error => {
        console.log(error);
      }
    )
  }

  addCmt() {
    return this.forumService.addComment(this.comment, this.id).subscribe(
      (data) => {
        window.location.reload()
      },
      error => {
        window.location.reload()
      }
    )
  }



}
