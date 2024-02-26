using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaPedidoDetalle
{
    public int IdPedidoDetalle { get; set; }

    public int IdPedido { get; set; }

    public int IdPlato { get; set; }

    public int Cantidad { get; set; }

    public virtual TaPedido IdPedidoNavigation { get; set; } = null!;

    public virtual TaPlato IdPlatoNavigation { get; set; } = null!;
}
