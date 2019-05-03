import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministrationCertificateComponent } from './administration-certificate.component';

describe('AdministrationCertificateComponent', () => {
  let component: AdministrationCertificateComponent;
  let fixture: ComponentFixture<AdministrationCertificateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministrationCertificateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministrationCertificateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
