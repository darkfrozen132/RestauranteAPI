
using Restaurante.Models;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Restaurante.Service;


var builder = WebApplication.CreateBuilder(args);
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

// Add services to the container.
builder.Services.Add(new ServiceDescriptor(typeof(IMesas), new MesasRepositorios()));
builder.Services.Add(new ServiceDescriptor(typeof(ICliente),new ClienteRepositorio())); 
builder.Services.Add(new ServiceDescriptor(typeof(IPedidocs),new PedidoRepositorio()));
builder.Services.Add(new ServiceDescriptor(typeof(IPedidoD), new PedidoDetalleRepositorio()));
builder.Services.Add(new ServiceDescriptor(typeof (IPersonal),new PersonalRespositorio()));
builder.Services.Add(new ServiceDescriptor(typeof(IPlato), new PlatoRepositorio()));
builder.Services.Add(new ServiceDescriptor(typeof(ITCliente), new TipoClienteRepositorio()));

builder.Services.AddControllers();
builder.Services.AddControllers();


//builder.Services.AddDbContext<DbContext>(options => options.UseSqlServer(connectionString));

 //Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
