import { Component, OnInit } from '@angular/core';
import { Discussion } from '../_Models/Discussion.model';
import { ForumService } from '../_services/forum.service';

@Component({
  selector: 'app-forum-stats',
  templateUrl: './forum-stats.component.html',
  styleUrls: ['./forum-stats.component.css']
})
export class ForumStatsComponent implements OnInit {

  discussions : Discussion[] = []
  constructor( private forumService: ForumService) { }

  ngOnInit(): void {
    this.getDiscussions()
  }

  getDiscussions() {
    this.forumService.getMostViewedDiscussions().subscribe(
      (data) => {
        this.discussions = data
        //console.log(this.discussions)
        this.discussions = this.discussions.slice(0, 3)
        console.log(this.discussions)
      }
    )
  }

}
