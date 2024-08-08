using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaPedido
{
    public int IdPedido { get; set; }

    public int IdMesa { get; set; }

    public int IdPersonal { get; set; }

    public int IdCliente { get; set; }

    public DateOnly Dia { get; set; }

    public TimeOnly? HoraInicioPedido { get; set; }

    public TimeOnly? HoraFinPedido { get; set; }

    public decimal? Total { get; set; }

    public virtual TaCliente? IdClienteNavigation { get; set; } = null;

    public virtual TaMesa? IdMesaNavigation { get; set; } = null;

    public virtual TaPersonal? IdPersonalNavigation { get; set; } = null;

    public virtual ICollection<TaPedidoDetalle> TaPedidoDetalles { get; set; } = new List<TaPedidoDetalle>();
}
