import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementHabilitationComponent } from './management-habilitation.component';

describe('ManagementHabilitationComponent', () => {
  let component: ManagementHabilitationComponent;
  let fixture: ComponentFixture<ManagementHabilitationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementHabilitationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementHabilitationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
