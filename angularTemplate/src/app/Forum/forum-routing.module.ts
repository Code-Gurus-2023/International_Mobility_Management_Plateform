import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForumMainPageComponent } from './forum-main-page/forum-main-page.component';
import { ForumDiscussionComponent } from './forum-discussion/forum-discussion.component';

const routes: Routes = [
  {path: '', component: ForumMainPageComponent},
  {path: 'discussion/:id', component: ForumDiscussionComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ForumRoutingModule { }
