import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementProfileComponent } from './management-profile.component';

describe('ManagementProfileComponent', () => {
  let component: ManagementProfileComponent;
  let fixture: ComponentFixture<ManagementProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
