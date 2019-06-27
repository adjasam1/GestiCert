import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './web-pages/login/login.component';
import {HomeComponent} from './web-pages/home/home.component';
import {CertificateComponent} from './web-pages/certificate/certificate.component';
import {ContactComponent} from './header-footer/contact/contact.component';
import {SiteComponent} from './header-footer/site/site.component';
import {AccessibiliteComponent} from './header-footer/accessibilite/accessibilite.component';
import {ProfileComponent} from './web-pages/profile/profile.component';
import {ManagementComponent} from './web-pages/administration/management/management.component';
import {NewDemandComponent} from './web-pages/demandCertificate/new-demand/new-demand.component';
import {DemandComponent} from './web-pages/demandCertificate/demand/demand.component';
import {AdminGuard} from './jwt-security/guards/admin.guard';
import {DevGuard} from './jwt-security/guards/dev.guard';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'accueil/:id1', component: HomeComponent, canActivateChild: [DevGuard] },
  { path: 'gestion', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/uti', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/uti/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/uti/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pro', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pro/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pro/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sce', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sce/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sce/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/hab', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/hab/app', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/hab/app/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/app', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/app/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/app/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/cer', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/cer/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/cer/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/env', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/env/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/env/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pla', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pla/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/pla/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/rac', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/rac/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/rac/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sur', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sur/:id', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'gestion/sur/ajout', component: ManagementComponent, canActivate: [AdminGuard] },
  { path: 'certificat/:id', component: CertificateComponent, canActivateChild: [DevGuard] },
  { path: 'contact', component: ContactComponent, canActivateChild: [DevGuard] },
  { path: 'site', component: SiteComponent, canActivateChild: [DevGuard] },
  { path: 'accessibilite', component: AccessibiliteComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/profil', component: ProfileComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/application', component: NewDemandComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/application/:id2', component: NewDemandComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/application/:id2/demande/:id3', component: NewDemandComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/certificat/:id2', component: DemandComponent, canActivateChild: [DevGuard] },
  { path: 'accueil/:id1/certificat/:id2/demande', component: DemandComponent, canActivateChild: [DevGuard] },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
