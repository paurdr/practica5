describe('Registro y login', () => {
  const email = Date.now() + '@email.com'
  const pass = 'aaaaaaA1';
  const name = 'Mr.Nombre'

  it('Registro correcto', () => {
    cy.visit('http://localhost:8080/registro.html')
    cy.get('[name="name"]').type(name)
    cy.get('[name="name"]').should('have.value', name)
    cy.get('[name="email"]').type(email)
    cy.get('[name="email"]').should('have.value', email)
    cy.get('[name="password"]').type(pass)
    cy.get('[name="password"]').should('have.value', pass)
    cy.get('[name="password2"]').type(pass)
    cy.get('[name="password2"]').should('have.value', pass)
    cy.contains('Registrarse').click()
    cy.url().should('include', '/login.html')
    cy.contains('¡Registrado! Prueba a entrar')
  })

  // TODO#13
  // Implementa el siguiente test E2E del frontend web para
  // verificar que se realiza el login correctamente con el usuario
  // previamente registrado
  it('Login correcto', () => {
    // Visitar la página de login
    cy.visit('http://localhost:8080/login.html');
    // Rellenar campos
    cy.get('[name="email"]').type(email);
    cy.get('[name="password"]').type(pass);
    // Pulsar Entrar
    cy.contains('Entrar').click();
    // Debe redirigir a app.html
    cy.url().should('include', '/app.html');
    // Y mostrarse el nombre en el encabezado
    cy.get('#nombre-inicio').should('have.text', name);
  });
  
})
