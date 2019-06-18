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
  { path: 'accueil/:id1', component: HomeComponent },
  { path: 'gestion', component: ManagementComponent },
  { path: 'gestion/uti', component: ManagementComponent },
  { path: 'gestion/uti/:id', component: ManagementComponent },
  { path: 'gestion/uti/ajout', component: ManagementComponent },
  { path: 'gestion/pro', component: ManagementComponent },
  { path: 'gestion/pro/:id', component: ManagementComponent },
  { path: 'gestion/pro/ajout', component: ManagementComponent },
  { path: 'gestion/sce', component: ManagementComponent },
  { path: 'gestion/sce/:id', component: ManagementComponent },
  { path: 'gestion/sce/ajout', component: ManagementComponent },
  { path: 'gestion/hab', component: ManagementComponent },
  { path: 'gestion/hab/app', component: ManagementComponent },
  { path: 'gestion/hab/app/:id', component: ManagementComponent },
  { path: 'gestion/app', component: ManagementComponent },
  { path: 'gestion/app/:id', component: ManagementComponent },
  { path: 'gestion/app/ajout', component: ManagementComponent },
  { path: 'gestion/cer', component: ManagementComponent },
  { path: 'gestion/cer/:id', component: ManagementComponent },
  { path: 'gestion/cer/ajout', component: ManagementComponent },
  { path: 'gestion/env', component: ManagementComponent },
  { path: 'gestion/env/:id', component: ManagementComponent },
  { path: 'gestion/env/ajout', component: ManagementComponent },
  { path: 'gestion/pla', component: ManagementComponent },
  { path: 'gestion/pla/:id', component: ManagementComponent },
  { path: 'gestion/pla/ajout', component: ManagementComponent },
  { path: 'gestion/rac', component: ManagementComponent },
  { path: 'gestion/rac/:id', component: ManagementComponent },
  { path: 'gestion/rac/ajout', component: ManagementComponent },
  { path: 'gestion/sur', component: ManagementComponent },
  { path: 'gestion/sur/:id', component: ManagementComponent },
  { path: 'gestion/sur/ajout', component: ManagementComponent },
  { path: 'certificat/:id', component: CertificateComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'site', component: SiteComponent },
  { path: 'accessibilite', component: AccessibiliteComponent },
  { path: 'accueil/:id1/profil', component: ProfileComponent },
  { path: 'accueil/:id1/application', component: NewDemandComponent },
  { path: 'accueil/:id1/application/:id2', component: NewDemandComponent },
  { path: 'accueil/:id1/application/:id2/demande/:id3', component: NewDemandComponent },
  { path: 'accueil/:id1/certificat/:id2', component: DemandComponent },
  { path: 'accueil/:id1/certificat/:id2/demande', component: DemandComponent },
  { path: '**', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
