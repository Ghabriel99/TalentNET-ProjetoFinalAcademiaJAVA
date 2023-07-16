import { Directive } from '@angular/core';
import {
  AbstractControl,
  NG_VALIDATORS,
  Validator,
  ValidatorFn,
} from '@angular/forms';

import { cpf } from 'cpf-cnpj-validator';

@Directive({
  selector: '[cpfValidator][ngModel]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: CpfValidatorDirective,
      multi: true,
    },
  ],
})

/**
 * @description
 * Method that performs synchronous validation against the provided control.
 *
 * @param control The control to validate against.
 *
 * @returns A map of validation errors if validation fails,
 * otherwise null.
 */
export class CpfValidatorDirective implements Validator {
  validate(control: AbstractControl): { [key: string]: any } | null {


    const cpfInserido = control.value;

    const isValid = cpf.isValid(cpfInserido);

    return isValid ? null : { cpfInvalido: true };
  }
}

export function cpfValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {

    return new CpfValidatorDirective().validate(control)
  };
}
