import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { SearchComponent } from './search/search.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  // { path: '', redirectTo: '/register', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  // { path: 'detail/:id', component: HeroDetailComponent },
  { path: 'search', component: SearchComponent },
  { path: 'update', component: UpdateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

