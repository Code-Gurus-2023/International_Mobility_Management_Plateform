import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAlertsComponent } from './add-alerts.component';

describe('AddAlertsComponent', () => {
  let component: AddAlertsComponent;
  let fixture: ComponentFixture<AddAlertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAlertsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAlertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
