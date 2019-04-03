import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthentificationComponent} from './authentication/authentification.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {AccueilComponent} from './home/accueil.component';
import {ProfileComponent} from './profile/profile.component';

const routes: Routes = [
  { path: '', component: AuthentificationComponent },
  { path: 'accueil/:id', component: AccueilComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'site', component: SiteComponent},
  { path: 'accessibilite', component: AccessibiliteComponent},
  { path: 'profil/:id', component: ProfileComponent},
  { path: 'profil/ajout', component: ProfileComponent},
  { path: '**', component: AuthentificationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
