/*
 * Copyright © 2021-2021 Thales Services Numériques
 *
 * Licensed under the Thales Inner Source Software License:
 *
 * Version 1.2, InnerOpen - Distribution Not Controlled
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at https://gitlab.thalesdigital.io/innersource/licenses
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.thalesgroup.codingacademy.template.elements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Elements API main class
 */
@SpringBootApplication(scanBasePackages = {"com.thalesgroup.codingacademy.template.elements"})
public class ElementsApplication {
	public static void main(String[] args) {
		// Don't use args until to be sure to sanitize the args before using it.
		SpringApplication.run(ElementsApplication.class);
	}
}
