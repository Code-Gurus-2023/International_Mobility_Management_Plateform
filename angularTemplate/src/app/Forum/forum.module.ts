import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ForumRoutingModule } from './forum-routing.module';
import { ForumMainPageComponent } from './forum-main-page/forum-main-page.component';
import { ForumTableComponent } from './forum-table/forum-table.component';
import { ForumContentComponent } from './forum-content/forum-content.component';
import { ForumService } from './_services/forum.service';
import { ForumAddQuestionComponent } from './forum-add-question/forum-add-question.component';
import { FormsModule } from '@angular/forms';
import { ForumDiscussionComponent } from './forum-discussion/forum-discussion.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { ForumStatsComponent } from './forum-stats/forum-stats.component';
import { PaginationModule } from '../pagination/pagination.module';


@NgModule({
  declarations: [
    ForumMainPageComponent,
    ForumTableComponent,
    ForumContentComponent,
    ForumAddQuestionComponent,
    ForumDiscussionComponent,
    ForumStatsComponent
  ],
  imports: [
    CommonModule,
    ForumRoutingModule,
    Ng2SearchPipeModule,
    FormsModule,
    PaginationModule
  ],
  providers: [
    ForumService
  ]
})
export class ForumModule { }
