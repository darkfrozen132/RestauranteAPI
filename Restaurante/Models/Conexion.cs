using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace Restaurante.Models;

public partial class Conexion : DbContext
{
    public Conexion()
    {
    }

    public Conexion(DbContextOptions<Conexion> options)
        : base(options)
    {
    }

    public virtual DbSet<TaCliente> TaClientes { get; set; }

    public virtual DbSet<TaMesa> TaMesas { get; set; }

    public virtual DbSet<TaPedido> TaPedidos { get; set; }

    public virtual DbSet<TaPedidoDetalle> TaPedidoDetalles { get; set; }

    public virtual DbSet<TaPersonal> TaPersonals { get; set; }

    public virtual DbSet<TaPlato> TaPlatos { get; set; }

    public virtual DbSet<TaTipoCliente> TaTipoClientes { get; set; } 

    public virtual DbSet<TaUsuario> TaUsuarios { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
     => optionsBuilder.UseSqlServer("Data Source=SQL8010.site4now.net;Initial Catalog=db_aab074_dbcuenta;User Id=db_aab074_dbcuenta_admin;Password=123456789smart");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<TaCliente>(entity =>
        {
            entity.HasKey(e => e.IdCliente).HasName("TA_cliente_pk");

            entity.ToTable("TA_cliente");

            entity.Property(e => e.IdCliente)
                .ValueGeneratedNever()
                .HasColumnName("id_cliente");
            entity.Property(e => e.IdTipoCliente).HasColumnName("id_tipo_cliente");
            entity.Property(e => e.Nombre)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("nombre");

            entity.HasOne(d => d.IdTipoClienteNavigation).WithMany(p => p.TaClientes)
                .HasForeignKey(d => d.IdTipoCliente)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_tipo_cliente_fk");
        });

        modelBuilder.Entity<TaMesa>(entity =>
        {
            entity.HasKey(e => e.IdMesa).HasName("TA_mesas_pk");

            entity.ToTable("TA_mesas");

            entity.Property(e => e.IdMesa)
                .ValueGeneratedNever()
                .HasColumnName("id_mesa");
            entity.Property(e => e.CantidadAsientos).HasColumnName("cantidad_asientos");
            entity.Property(e => e.Descripcion)
                .HasMaxLength(25)
                .IsUnicode(false)
                .HasColumnName("descripcion");
            entity.Property(e => e.Reservado).HasColumnName("reservado");
            entity.Property(e => e.dLeft).ValueGeneratedNever().HasColumnName("dleft");
            entity.Property(e => e.dUp).ValueGeneratedNever().HasColumnName("dup");
        });

        modelBuilder.Entity<TaPedido>(entity =>
        {
            entity.HasKey(e => e.IdPedido).HasName("TA_pedido_pk");

            entity.ToTable("TA_pedido");

            entity.Property(e => e.IdPedido)
                .ValueGeneratedNever()
                .HasColumnName("id_pedido");
            entity.Property(e => e.Dia).HasColumnName("dia");
            entity.Property(e => e.HoraFinPedido).HasColumnName("hora_fin_pedido");
            entity.Property(e => e.HoraInicioPedido).HasColumnName("hora_inicio_pedido");
            entity.Property(e => e.IdCliente).HasColumnName("id_cliente");
            entity.Property(e => e.IdMesa).HasColumnName("id_mesa");
            entity.Property(e => e.IdPersonal).HasColumnName("id_personal");
            entity.Property(e => e.Total)
                .HasColumnType("decimal(5, 1)")
                .HasColumnName("total");

            entity.HasOne(d => d.IdClienteNavigation).WithMany(p => p.TaPedidos)
                .HasForeignKey(d => d.IdCliente)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_cliente_fk");

            entity.HasOne(d => d.IdMesaNavigation).WithMany(p => p.TaPedidos)
                .HasForeignKey(d => d.IdMesa)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_mesa_fk");

            entity.HasOne(d => d.IdPersonalNavigation).WithMany(p => p.TaPedidos)
                .HasForeignKey(d => d.IdPersonal)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_personal_fk");
        });

        modelBuilder.Entity<TaPedidoDetalle>(entity =>
        {
            entity.HasKey(e => e.IdPedidoDetalle).HasName("TA_pedido_detalle_pk");

            entity.ToTable("TA_pedido_detalle");

            entity.Property(e => e.IdPedidoDetalle)
                .ValueGeneratedNever()
                .HasColumnName("id_pedido_detalle");
            entity.Property(e => e.Cantidad).HasColumnName("cantidad");
            entity.Property(e => e.IdPedido).HasColumnName("id_pedido");
            entity.Property(e => e.IdPlato).HasColumnName("id_plato");

            entity.HasOne(d => d.IdPedidoNavigation).WithMany(p => p.TaPedidoDetalles)
                .HasForeignKey(d => d.IdPedido)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_pedido_fk");

            entity.HasOne(d => d.IdPlatoNavigation).WithMany(p => p.TaPedidoDetalles)
                .HasForeignKey(d => d.IdPlato)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("TA_plato_fk");
        });

        modelBuilder.Entity<TaPersonal>(entity =>
        {
            entity.HasKey(e => e.IdPersonal).HasName("TA_personal_pk");

            entity.ToTable("TA_personal");

            entity.Property(e => e.IdPersonal)
                .ValueGeneratedNever()
                .HasColumnName("id_personal");
            entity.Property(e => e.NombrePersonal)
                .HasMaxLength(25)
                .IsUnicode(false)
                .HasColumnName("nombre_personal");
            entity.Property(e => e.Permisos).HasColumnName("permisos");
        });

        modelBuilder.Entity<TaPlato>(entity =>
        {
            entity.HasKey(e => e.IdPlato).HasName("TA_platos_pk");

            entity.ToTable("TA_plato");

            entity.Property(e => e.IdPlato)
                .ValueGeneratedNever()
                .HasColumnName("id_plato");
            entity.Property(e => e.Costo)
                .HasColumnType("decimal(3, 1)")
                .HasColumnName("costo");
            entity.Property(e => e.Disponible).HasColumnName("disponible");
            entity.Property(e => e.NombrePlato)
                .HasMaxLength(25)
                .IsUnicode(false)
                .HasColumnName("nombre_plato");
        });

        modelBuilder.Entity<TaTipoCliente>(entity =>
        {
            entity.HasKey(e => e.IdTipoCliente).HasName("TA_tipo_cliente_pk");

            entity.ToTable("TA_tipo_cliente");

            entity.Property(e => e.IdTipoCliente)
                .ValueGeneratedNever()
                .HasColumnName("id_tipo_cliente");
            entity.Property(e => e.Porcentaje).HasColumnName("porcentaje");
        });

        modelBuilder.Entity<TaUsuario>(entity =>
        {
            entity.HasKey(e => e.IdUsuario).HasName("TA_usuario_pk");

            entity.ToTable("TA_usuario");

            entity.Property(e => e.IdUsuario)
                .ValueGeneratedNever()
                .HasColumnName("id_usuario");

            entity.Property(e => e.Categoria)
                .HasColumnName("categoria");

            entity.Property(e => e.Usuario)
                .HasColumnName("usuario")
                .IsRequired();

            entity.Property(e => e.Contrasena)
                .HasColumnName("contrasena")
                .IsRequired();
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
