using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaPlato
{
    public int IdPlato { get; set; }

    public string NombrePlato { get; set; } = null!;

    public decimal Costo { get; set; }

    public int Disponible { get; set; }

    public virtual ICollection<TaPedidoDetalle> TaPedidoDetalles { get; set; } = new List<TaPedidoDetalle>();
}
