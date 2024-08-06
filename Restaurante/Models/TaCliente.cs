using System;
using System.Collections.Generic;

namespace Restaurante.Models;

public partial class TaCliente
{
    public int IdCliente { get; set; }

    public string Nombre { get; set; } = null!;

    public int IdTipoCliente { get; set; }

    public virtual TaTipoCliente? IdTipoClienteNavigation { get; set; } = null;

    public virtual ICollection<TaPedido> TaPedidos { get; set; } = new List<TaPedido>();
}
