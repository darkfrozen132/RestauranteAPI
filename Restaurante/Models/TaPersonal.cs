using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaPersonal
{
    public int IdPersonal { get; set; }

    public string? NombrePersonal { get; set; }

    public int? Permisos { get; set; }

    public virtual ICollection<TaPedido> TaPedidos { get; set; } = new List<TaPedido>();
}
