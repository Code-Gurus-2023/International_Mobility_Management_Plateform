import { Component, OnInit } from '@angular/core';
import { Discussion } from 'src/app/Forum/_Models/Discussion.model';
import { ForumService } from '../../services/forum.service';

@Component({
  selector: 'app-forum-table',
  templateUrl: './forum-table.component.html',
  styleUrls: ['./forum-table.component.css']
})
export class ForumTableComponent implements OnInit {

  discussions: Discussion[] = []
  p: number = 1;
  constructor(private discussionService: ForumService) { }

  ngOnInit(): void {
    this.getAll()
  }

  getAll() {
    this.discussionService.getDiscussions().subscribe(
      (data) => {
        this.discussions = data
        console.log(this.discussions)
      }
    )
  }

}
