import { Component, OnInit } from '@angular/core';
import { Discussion } from '../_Models/Discussion.model';
import { ForumService } from '../_services/forum.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forum-table',
  templateUrl: './forum-table.component.html',
  styleUrls: ['./forum-table.component.css']
})
export class ForumTableComponent implements OnInit {

  discussions: Discussion[] = [];
  question: any;
  p: number = 1;
  constructor(private forumService: ForumService, private router: Router) { }

  ngOnInit(): void {
    this.getAll()
  }

  getAll() {
    this.forumService.getDiscussions().subscribe(
      (data) => {
        this.discussions = data
        //console.log(this.discussions)
      }
    )
  }

  search() {
    if( this.question == "")
      this.ngOnInit();
    else
      this.discussions.filter( res => {
        //console.log("hello")
        return res.nameDsc?.toLocaleLowerCase().match(this.question.toLocaleLowerCase())
      })
  }

}
