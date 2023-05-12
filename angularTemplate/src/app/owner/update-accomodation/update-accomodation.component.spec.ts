import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAccomodationComponent } from './update-accomodation.component';

describe('UpdateAccomodationComponent', () => {
  let component: UpdateAccomodationComponent;
  let fixture: ComponentFixture<UpdateAccomodationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateAccomodationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAccomodationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
