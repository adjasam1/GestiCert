import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagementRootComponent } from './management-root.component';

describe('ManagementRootComponent', () => {
  let component: ManagementRootComponent;
  let fixture: ComponentFixture<ManagementRootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagementRootComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagementRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
